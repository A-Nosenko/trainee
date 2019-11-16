import * as types from '../constants/ActionTypes';
import {store} from '../index';

const initState = {
    ip: "127.0.0.1",
    port: "3306",
    login: "root",
    password: "root",
    connectionStatus: "",
};

const connectionReducer = (state = initState, action) => {

    switch (action.type) {
        case types.UPDATE_IP:
            return {
                ...state,
                ip: action.payload
            };

        case types.UPDATE_PORT:
            return {
                ...state,
                port: action.payload
            };

        case types.UPDATE_LOGIN:
            return {
                ...state,
                login: action.payload
            };

        case types.UPDATE_PASSWORD:
            return {
                ...state,
                password: action.payload
            };

        case types.CREATE_CONNECTION:
            fetch('http://localhost:8080/connect', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },

                body: JSON.stringify({
                    'ip': state.ip,
                    'port': state.port,
                    'login': state.login,
                    'password': state.password
                })
            })
                .then(response => response.json())
                .then(data => {
                    let node = JSON.parse(data.root);
                    store.dispatch({
                        type: types.UPDATE_CONNECTION,
                        payload: {
                            status: data.connector,
                        }

                    });
                    store.dispatch({
                        type: types.UPDATE_ROOT,
                        payload: {
                            rootNewId: node.item.uniqueId,
                            rootNode: node
                        }
                    });
                })
                .catch((e) => {
                        console.log(e.toString());
                    }
                );

            return {
                ...state,
                connectionStatus: "",
            };

        case types.UPDATE_CONNECTION:
            return {
                ...state,
                connectionStatus: action.payload.status,
            };

        default:
            return state;
    }
};

export default connectionReducer;
