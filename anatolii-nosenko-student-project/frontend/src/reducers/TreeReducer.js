import * as types from '../constants/ActionTypes';
import {store} from "../index";

const initState = {
    rootId: "",
    nodes: [],
    itemToShow: null
};

const treeReducer = (state = initState, action) => {

    switch (action.type) {
        case types.SAVE_TREE_TO_XML:
            fetch('http://localhost:8080/save')
                .then(response => {
                    if (response.ok && !(response.status === 409)) {
                        window.alert("Tree saving to XML completed.");
                    } else {
                        window.alert("Tree saving to XML failed. Tree not initialized on server.");
                    }
                })
                .catch((e) => {
                        console.log(e.toString());
                    }
                );
            return state;

        case types.LOAD_TREE_FROM_XML:
            fetch('http://localhost:8080/load')
                .then(response => response.json())
                .then(data => {
                    let node = JSON.parse(data.root);
                    store.dispatch({
                        type: types.UPDATE_CONNECTION,
                        payload: {
                            status: data.connector,
                        }

                    });
                    store.dispatch({
                        type: types.UPDATE_ROOT,
                        payload: {
                            rootNewId: node.item.uniqueId,
                            rootNode: node
                        }
                    });
                })
                .catch((e) => {
                        console.log(e.toString());
                    }
                );
            return state;

        case types.UPDATE_ROOT:
            return {
                ...state,
                rootId: action.payload.rootNewId,
                nodes: [action.payload.rootNode],
                itemToShow: null
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
                    .concat(action.payload.nodes)
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
