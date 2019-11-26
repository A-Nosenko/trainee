import * as types from '../constants/ActionTypes';

export function updateTypeToSearch(typeToSearch) {
    return {
        type: types.UPDATE_SEARCH_TYPE,
        payload: {typeToSearch}
    }
}

export function updateValueToSearch(valueToSearch) {
    return {
        type: types.UPDATE_SEARCH_VALUE,
        payload: {valueToSearch}
    }
}
