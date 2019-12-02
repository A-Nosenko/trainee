import React, {Component} from 'react';
import {connect} from 'react-redux';
import {MDBRow} from 'mdbreact';
import {getConnectionPropertiesSelector} from '../selectors/selectors';

import {createConnection, updateIp, updateLogin, updatePassword, updatePort} from '../actions/ConnectionActions';

class ConnectionForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ipError: this.checkIp(props.connectionProperties.ip),
            portError: this.checkPort(props.connectionProperties.port),
            loginError: this.checkLogin(props.connectionProperties.login),
            passwordError: this.checkPassword(props.connectionProperties.password)
        }
    }

    submitHandler = (event) => {
        event.preventDefault();
        this.props.submitConnectionForm();
    };

    checkIp = (ip) => {
        if (!ip) {
            return "Ip can't be empty!";
        } else if (ip.toUpperCase() === "LOCALHOST") {
            return '';
        } else if (!/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ip)) {
            return "Incorrect ip!"
        }
        return '';
    };

    checkPort = (port) => {
        if (!port || port < 1 || port > 65535) {
            return "Incorrect port!";
        }
        return '';
    };

    checkLogin = (login) => {
        if (!login) {
            return "Login can't be empty!";
        } else if (login.length < 3) {
            return "Login too short!";
        } else if (login.length > 100) {
            return "Login too long!";
        }
        return '';
    };

    checkPassword = (password) => {
        if (!password) {
            return "Password can't be empty!";
        } else if (password.length < 3) {
            return "Password too short!";
        } else if (password.length > 100) {
            return "Password too long!";
        }
        return '';
    };

    isFormValid = () => {
        return !this.state.ipError
            && !this.state.portError
            && !this.state.loginError
            && !this.state.passwordError;
    };

    render() {
        return (
            <form onSubmit={this.submitHandler}>

                <div>
                    <div className='ConnectionForm'>
                        <MDBRow>
                            <label htmlFor="ip">
                                <span>IP</span>
                            </label>
                            <input
                                id='ip'
                                type='text'
                                value={this.props.connectionProperties.ip}
                                onChange={
                                    event => {
                                        this.setState({ipError: this.checkIp(event.target.value)});
                                        this.props.inputIp(event.target.value)
                                    }
                                }
                                className={!this.state.ipError ? "form-control is-valid" : "form-control is-invalid"}
                            />
                            <div className="invalid-feedback">{this.state.ipError}</div>
                            <div className="valid-feedback Invisible">_</div>
                        </MDBRow>
                        <MDBRow>
                            <label htmlFor="port">
                                <span>Port</span>
                            </label>
                            <input
                                id='port'
                                type='number'
                                value={this.props.connectionProperties.port}
                                onChange={
                                    event => {
                                        this.setState({portError: this.checkPort(event.target.value)});
                                        this.props.inputPort(event.target.value)
                                    }
                                }
                                className={!this.state.portError ? "form-control is-valid" : "form-control is-invalid"}
                            />
                            <div className="invalid-feedback">{this.state.portError}</div>
                            <div className="valid-feedback Invisible">_</div>
                        </MDBRow>
                        <MDBRow>
                            <label htmlFor="login">
                                <span>Login</span>
                            </label>
                            <input
                                id='login'
                                type='text'
                                value={this.props.connectionProperties.login}
                                onChange={
                                    event => {
                                        this.setState({loginError: this.checkLogin(event.target.value)});
                                        this.props.inputLogin(event.target.value)
                                    }
                                }
                                className={!this.state.loginError ? "form-control is-valid" : "form-control is-invalid"}
                            />
                            <div className="invalid-feedback">{this.state.loginError}</div>
                            <div className="valid-feedback Invisible">_</div>
                        </MDBRow>
                        <MDBRow>
                            <label htmlFor="password">
                                <span>Password</span>
                            </label>
                            <input
                                id='password'
                                type='password'
                                value={this.props.connectionProperties.password}
                                onChange={
                                    event => {
                                        this.setState({passwordError: this.checkPassword(event.target.value)});
                                        this.props.inputPassword(event.target.value)
                                    }
                                }
                                className={!this.state.passwordError ? "form-control is-valid" : "form-control is-invalid"}
                            />
                            <div className="invalid-feedback">{this.state.passwordError}</div>
                            <div className="valid-feedback Invisible">_</div>
                        </MDBRow>
                        <MDBRow className="Center-container">
                            <button className='button'
                                    type='submit'
                                    disabled={!this.isFormValid()}>Connect
                            </button>

                        </MDBRow>
                    </div>
                </div>
            </form>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        connectionProperties: getConnectionPropertiesSelector(state)
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        inputIp: (ip) => {
            dispatch(updateIp(ip));
        },
        inputPort: (port) => {
            dispatch(updatePort(port));
        },
        inputLogin: (login) => {
            dispatch(updateLogin(login));
        },
        inputPassword: (password) => {
            dispatch(updatePassword(password));
        },
        submitConnectionForm: () => {
            dispatch(createConnection());
        }
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ConnectionForm);
