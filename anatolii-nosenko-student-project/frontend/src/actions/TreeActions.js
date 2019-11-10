import * as types from '../constants/ActionTypes'

export function openNode(id) {
    return {
        type: types.OPEN_NODE,
        payload: {id}
    }
}

export function closeNode(id) {
    return {
        type: types.CLOSE_NODE,
        payload: {id}
    }
}
