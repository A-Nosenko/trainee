import React, {Component} from 'react';
import Node from "./Node";

class NodeWrapper extends Component {

    render() {
        return (
            <Node id={this.props.id}/>
        )
    }
}

export default NodeWrapper;
