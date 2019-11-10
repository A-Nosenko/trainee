import React, {Component} from 'react';
import {connect} from "react-redux";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import {getConnectionPropertiesSelector} from '../selectors/selectors'

import {updateIp, updatePort, updateLogin, updatePassword, createConnection}
    from '../actions/ConnectionActions';

class ConnectionForm extends Component {

    submitHandler = (event) => {
        event.preventDefault();
        this.props.submitConnectionForm();
    };

    render() {
        return (
            <form onSubmit={this.submitHandler}>

                <div className='ConnectionForm'>
                    <Table>
                        <TableBody>
                            <TableRow>
                                <TableCell><span>IP</span></TableCell>
                                <TableCell><input
                                    id='ip'
                                    type='text'
                                    value={this.props.connectionProperties.ip}
                                    onChange={event => this.props.inputIp(event.target.value)}/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell><span>Port</span></TableCell>
                                <TableCell><input
                                    id='port'
                                    type='number'
                                    value={this.props.connectionProperties.port}
                                    onChange={event => this.props.inputPort(event.target.value)}/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell><span>Login</span></TableCell>
                                <TableCell><input
                                    id='login'
                                    type='text'
                                    value={this.props.connectionProperties.login}
                                    onChange={event => this.props.inputLogin(event.target.value)}/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell><span>Password</span></TableCell>
                                <TableCell><input
                                    id='password'
                                    type='password'
                                    value={this.props.connectionProperties.password}
                                    onChange={event => this.props.inputPassword(event.target.value)}/></TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell> </TableCell>
                                <TableCell>
                                    <button className='button' type='submit'
                                        // disabled={this.props.trueInput ? undefined:'true'}
                                    >Connect
                                    </button>
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
