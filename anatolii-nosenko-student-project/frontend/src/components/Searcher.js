import React, {Component} from 'react';
import {updateTypeToSearch, updateValueToSearch} from '../actions/SearchActions';
import {connect} from 'react-redux';

class Searcher extends Component {

    render() {
        return (
            <div className='Status-bar-div'>
                <select onChange={event => this.props.setType(event.target.value)}>
                    <option value='' defaultValue>Select item type</option>
                    <option value='database'>Database</option>
                    <option value='function'>Function</option>
                    <option value='stored_procedure'>Stored procedure</option>
                    <option value='table'>Table</option>
                    <option value='trigger'>Trigger</option>
                    <option value='view'>View</option>
                    <option value='column'>Column</option>
                    <option value='key'>Key</option>
                </select>
                <input type='text' placeholder='Search' onChange={event => this.props.setValue(event.target.value)}/>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        setType: (type) => {
            dispatch(updateTypeToSearch(type));
        },
        setValue: (value) => {
            dispatch(updateValueToSearch(value));
        },
    }
};

export default connect(null, mapDispatchToProps)(Searcher);
