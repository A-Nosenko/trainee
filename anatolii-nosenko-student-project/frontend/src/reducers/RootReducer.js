import {combineReducers} from 'redux';
import connectionReducer from './ConnectionReducer';
import treeReducer from './TreeReducer';
import searcherReducer from './SearcherReducer';

export const rootReducer = combineReducers({
    connection: connectionReducer,
    tree: treeReducer,
    searcher: searcherReducer
});
