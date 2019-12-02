import React from 'react';
import {createStore} from 'redux';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {rootReducer} from './reducers/RootReducer'
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import './index.css';

export const store = createStore(rootReducer);

ReactDOM.render(<Provider store={store}><App/></Provider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
