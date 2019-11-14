import {combineReducers, createStore} from 'redux';
import connectionReducer from './ConnectionReducer';
import treeReducer from './TreeReducer';

export const rootReducer = combineReducers({
    connection: connectionReducer,
    tree: treeReducer
});
