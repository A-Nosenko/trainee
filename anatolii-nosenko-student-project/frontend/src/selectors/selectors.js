import {createSelector} from 'reselect';

export const getRootNodeIdSelector = state => {
    return {
        id: state.tree.rootId,
    }
};

export const getConnectionStatusSelector = state => {
    return {
        status: state.connection.connectionStatus
    }
};

export const getConnectionPropertiesSelector = state => {
    return {
        ip: state.connection.ip,
        port: state.connection.port,
        login: state.connection.login,
        password: state.connection.password,
        rootId: state.tree.rootId,
    }
};

const selectNodes = state => state.tree.nodes;
const selectId = (state, nodeId) => nodeId;

export const findTheNodeSelector = createSelector(
    [selectNodes, selectId],
    (nodes, id) => {
        return {
            node: nodes.find(node => node.item.uniqueId === id)
        }
    });

const selectFilter = state => {
    return {
        type: state.searcher.typeToSearch,
        value: state.searcher.valueToSearch
    }
};

export const getFilter = createSelector(
    [selectFilter, selectNodes, selectId],
    (filter, nodes, id) => {
        if (!filter.type && !filter.value) {
            return true;
        } else {
            let currentNode = nodes.find(node => node.item.uniqueId === id);
            if (currentNode.item.tagName.startsWith(filter.type)) {
                if (!filter.value) {
                    return true;
                } else if (currentNode.item.tagName.includes(filter.value)) {
                    return true;
                }
            }
            return false;
        }
    }
);

export const getItemToShow = state => {
    return {
        item: state.tree.itemToShow
    }
};
