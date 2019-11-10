export function getRootNodeIdSelector(state) {
    return {
        id: state.connectionReducer.rootId,
    }
}

export function getConnectionStatusSelector(state) {
    return {
        status: state.connectionReducer.connectionStatus
    }
}

export function getConnectionPropertiesSelector(state) {
    return {
        ip: state.connectionReducer.ip,
        port: state.connectionReducer.port,
        login: state.connectionReducer.login,
        password: state.connectionReducer.password,
        rootId: state.connectionReducer.rootId,
    }
}

export function findTheNodeSelector(state, id) {
    return {
        node: state.connectionReducer.nodes.find(node => node.item.uniqueId === id)
    }
}

