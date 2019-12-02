import React, {Component} from 'react';
import {connect} from 'react-redux';
import {getConnectionStatusSelector} from '../selectors/selectors';
import {loadTreeFromXML, saveTreeToXML} from '../actions/TreeActions';

class StatusBar extends Component {
    render() {
        return (
            <div className='Status-bar-div'>
                <div className='Save-load-tree-div'>
                    <button className='button' onClick={this.props.saveTree}>Save tree</button>
                    <button className='button' onClick={this.props.loadTree}>Load tree</button>
                </div>
                <div className='Status-div'>
                    {(this.props.status)
                        ? (<span>{this.props.status}</span>)
                        : (<span className='error'>Disconnected</span>)}
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        status: getConnectionStatusSelector(state).status
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        saveTree: () => {
            dispatch(saveTreeToXML());
        },
        loadTree: () => {
            dispatch(loadTreeFromXML());
        }
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(StatusBar);
