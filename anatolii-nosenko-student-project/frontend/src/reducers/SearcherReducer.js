import * as types from '../constants/ActionTypes';

const initState = {
    typeToSearch: "",
    valueToSearch: ""
};

const searcherReducer = (state = initState, action) => {
    switch (action.type) {
        case types.UPDATE_SEARCH_TYPE: {
            return {
                ...state,
                typeToSearch: action.payload.typeToSearch
            };
        }

        case types.UPDATE_SEARCH_VALUE: {
            return {
                ...state,
                valueToSearch: action.payload.valueToSearch
            };
        }

        default:
            return state;
    }
};

export default searcherReducer;
