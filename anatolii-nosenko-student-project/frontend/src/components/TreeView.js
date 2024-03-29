import React, {Component} from 'react';
import Node from './Node';
import Table from './Table';
import {getRootNodeIdSelector} from '../selectors/selectors';
import {connect} from 'react-redux';
import Searcher from "./Searcher";

class TreeView extends Component {

    render() {
        return (
            <div className='Main-container'>
                <Searcher/>
                <div className='Database-tree-table'>
                    <div className='Tree'>
                        {
                            this.props.root.id
                                ? <Node id={this.props.root.id}/>
                                : <span className='error'>Can't open tree without connection.</span>
                        }
                    </div>
                    <div className='Table'>
                        <Table/>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        root: getRootNodeIdSelector(state)
    }
};

export default connect(mapStateToProps)(TreeView);
