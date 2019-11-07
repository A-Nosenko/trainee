export function getRootNode(state) {
    return {
        id: state.connectionReducer.rootId,
        name: state.connectionReducer.rootName
    }
}
