import React, {Component} from 'react';
import {connect} from 'react-redux';
import Open from '../images/open.png';
import Close from '../images/close.png';
import {getRootNode} from '../selectors/selectors';
import Node from "./Node";

class RootNode extends Component {
    constructor(props) {
        super(props);
        this.state = {
            image: props.root.id ? Open : null,
            childNodes: []
        }
    }

    onClick = () => {
        if (!this.props.root.id) {
            return;
        }
        this.state.image === Open
            ? this.openNode()
            : this.closeNode()
    };

    openNode = () => {
        this.setState({image: Close});
        fetch('http://localhost:8080/open?id=' + this.props.root.id)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.setState({childNodes: data});
            })
            .catch((e) => {
                    console.log(e.toString());
                }
            );
    };

    closeNode = () => {
        this.setState({image: Open});
        this.setState({childNodes: []});
    };

    render() {
        return (
            this.props.root.id
                ? <ul>
                    <img onClick={this.onClick} src={this.state.image} alt={this.props.root.name}/>
                    <span className='cursor'>{this.props.root.name}</span>
                    {this.state.childNodes.map(
                        function (node, key) {
                            return (
                                <Node nodeId={node.item.uniqueId}
                                      name={node.item.tagName}
                                      key={key}
                                      end={node.isFinalNode}/>
                            )
                        }
                    )}
                </ul>
                : <span className='error'>Can't open tree without connection.</span>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        root: getRootNode(state)
    }
};

export default connect(mapStateToProps)(RootNode);
