import * as types from '../constants/ActionTypes';
import {store} from "../index";

const initState = {
    rootId: "",
    nodes: [],
    itemToShow: null
};

const treeReducer = (state = initState, action) => {

    switch (action.type) {

        case types.UPDATE_CONNECTION:
            return {
                ...state,
                connectionStatus: action.payload.status,
                rootId: action.payload.rootNewId,
                nodes: [action.payload.rootNode]
            };

        case types.OPEN_NODE:
            fetch('http://localhost:8080/open?id=' + action.payload.id)
                .then(response => response.json())
                .then(data => {
                    store.dispatch({
                        type: types.ADD_CHILD_NODES,
                        payload: {
                            id: action.payload.id,
                            nodes: data
                        }
                    });
                })
                .catch((e) => {
                        console.log(e.toString());
                    }
                );
            return state;

        case types.ADD_CHILD_NODES:
            let nodeToOpen = state.nodes.find(node => node.item.uniqueId === action.payload.id);
            let overriddenNodeToOpen = {
                item: nodeToOpen.item,
                isFinalNode: nodeToOpen.isFinalNode,
                childTreeNodes: action.payload.nodes.map(node => node.item.uniqueId)
            };

            return {
                ...state,
                nodes: state
                    .nodes
                    .filter(node => node.item.uniqueId !== action.payload.id)
                    .concat(action.payload.nodes.map(function (node) {
                        return {
                            item: node.item,
                            isFinalNode: node.isFinalNode,
                            childTreeNodes: []
                        }
                    }))
                    .concat(overriddenNodeToOpen)
            };

        case types.CLOSE_NODE:
            let nodeToClose = state.nodes.find(node => node.item.uniqueId === action.payload.id);

            if (!nodeToClose) {
                return state;
            }

            if (!nodeToClose.childTreeNodes) {
                return state;
            }

            let currentNodeIdWithChildIdes = nodeToClose.childTreeNodes.concat(action.payload.id);

            let overriddenNodeToClose = {
                item: nodeToClose.item,
                childTreeNodes: [],
                isFinalNode: nodeToClose.isFinalNode,
            };

            if (currentNodeIdWithChildIdes.length > 1) {
                let newNodes = state
                    .nodes
                    .filter(node => !currentNodeIdWithChildIdes.includes(node.item.uniqueId))
                    .concat(overriddenNodeToClose);
                return {
                    ...state,
                    nodes: newNodes
                };
            }

            return state;

        case types.SHOW_NODE:
            return {
                ...state,
                itemToShow: action.payload
            };

        default:
            return state;
    }
};

export default treeReducer;