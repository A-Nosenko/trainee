import {createSelector} from "reselect";

export function getRootNodeIdSelector(state) {
    return {
        id: state.tree.rootId,
    }
}

export function getConnectionStatusSelector(state) {
    return {
        status: state.connection.connectionStatus
    }
}

export function getConnectionPropertiesSelector(state) {
    return {
        ip: state.connection.ip,
        port: state.connection.port,
        login: state.connection.login,
        password: state.connection.password,
        rootId: state.tree.rootId,
    }
}

const selectNodes = state => state.tree.nodes;
const selectId = (state, nodeId) => nodeId;

export const findTheNodeSelector = createSelector(
        [selectNodes, selectId],
        (nodes, id) => {
            return {
                node: nodes.find(node => node.item.uniqueId === id)
            }
        });

export function getItemToShow(state) {
    return {
        item: state.tree.itemToShow
    }
}
