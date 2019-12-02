import * as types from '../constants/ActionTypes';

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

export function showNode(item) {
    return {
        type: types.SHOW_NODE,
        payload: {item}
    }
}

export function loadTreeFromXML() {
    return {
        type: types.LOAD_TREE_FROM_XML
    }
}

export function saveTreeToXML() {
    return {
        type: types.SAVE_TREE_TO_XML
    }
}
