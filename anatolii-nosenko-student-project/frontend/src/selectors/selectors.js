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

export function findTheNodeSelector(state, id) {
    return {
        node: state.tree.nodes.find(node => node.item.uniqueId === id)
    }
}

export function getItemToShow(state) {
    return {
        item: state.tree.itemToShow
    }
}
