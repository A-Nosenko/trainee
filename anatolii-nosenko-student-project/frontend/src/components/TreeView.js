import React, {Component} from 'react';
import RootNode from './RootNode'
import Table from './Table'

class TreeView extends Component {

    render() {
        return (
            <div className='Database-tree'>
                <div className='Tree'>
                    <RootNode/>
                </div>
                <div className='Table'>
                    <Table/>
                </div>
            </div>
        );
    }
}

export default TreeView;
