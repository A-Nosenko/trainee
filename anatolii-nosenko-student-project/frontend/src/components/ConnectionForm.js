import React, {Component} from 'react';
import {connect} from "react-redux";
import {MDBInput} from "mdbreact";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import {getConnectionPropertiesSelector} from '../selectors/selectors'

import {updateIp, updatePort, updateLogin, updatePassword, createConnection}
    from '../actions/ConnectionActions';

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
        if (!port) {
            return "Port can't be empty!";
        }
        if (port < 1 || port > 65535) {
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

    styles = theme => ({
        container: {
            color: 'whitesmoke',
        }
    });

    render() {
        return (
            <form onSubmit={this.submitHandler}>

                <div className='ConnectionForm'>
                    <Table>
                        <TableBody>
                            <TableRow>
                                <TableCell><span>IP</span></TableCell>
                                <TableCell><MDBInput
                                    id='ip'
                                    type='text'
                                    value={this.props.connectionProperties.ip}
                                    onChange={
                                        event => {
                                            this.setState({ipError: this.checkIp(event.target.value)});
                                            this.props.inputIp(event.target.value)
                                        }
                                    }/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell><span>Port</span></TableCell>
                                <TableCell><MDBInput
                                    id='port'
                                    type='number'
                                    value={this.props.connectionProperties.port}
                                    onChange={
                                        event => {
                                            this.setState({portError: this.checkPort(event.target.value)});
                                            this.props.inputPort(event.target.value)
                                        }
                                    }/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell><span>Login</span></TableCell>
                                <TableCell><MDBInput
                                    id='login'
                                    type='text'
                                    value={this.props.connectionProperties.login}
                                    onChange={
                                        event => {
                                            this.setState({loginError: this.checkLogin(event.target.value)});
                                            this.props.inputLogin(event.target.value)
                                        }
                                    }/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell><span>Password</span></TableCell>
                                <TableCell><MDBInput
                                    id='password'
                                    type='password'
                                    value={this.props.connectionProperties.password}
                                    onChange={
                                        event => {
                                            this.setState({passwordError: this.checkPassword(event.target.value)});
                                            this.props.inputPassword(event.target.value)
                                        }
                                    }/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell> </TableCell>
                                <TableCell>
                                    {
                                        this.isFormValid() ?
                                            <div className='SubmitBlock'>
                                                <button className='button' type='submit'>Connect</button>
                                            </div>
                                            : <div className='SubmitBlock'>
                                                <span className='error'>{this.state.ipError}</span>
                                                <span className='error'>{this.state.portError}</span>
                                                <span className='error'>{this.state.loginError}</span>
                                                <span className='error'>{this.state.passwordError}</span>
                                            </div>
                                    }
                                </TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
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
