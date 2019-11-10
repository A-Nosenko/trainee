import * as types from '../constants/ActionTypes';

export function updateIp(ip) {
    return {
        type: types.UPDATE_IP,
        payload: ip
    }
}

export function updatePort(port) {
    return {
        type: types.UPDATE_PORT,
        payload: port
    }
}

export function updateLogin(login) {
    return {
        type: types.UPDATE_LOGIN,
        payload: login
    }
}

export function updatePassword(password) {
    return {
        type: types.UPDATE_PASSWORD,
        payload: password
    }
}

export function createConnection() {
    return {
        type: types.CREATE_CONNECTION
    }
}
