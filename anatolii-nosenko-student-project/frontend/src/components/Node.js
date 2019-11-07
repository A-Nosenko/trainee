import React, {Component} from 'react';
import Open from '../images/open.png';
import Close from '../images/close.png';
import EndPoint from '../images/end_point.png';

class Node extends Component {
    constructor(props) {
        super(props);
        this.state = {
            image: Open,
            childNodes: [],
            end: props.end
        }
    }

    onClick = () => {
        this.state.image === Open
            ? this.openNode()
            : this.closeNode()
    };

    openNode = () => {
        this.setState({image: Close});
        fetch('http://localhost:8080/open?id=' + this.props.nodeId)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.setState({childNodes: data});
                if (!data.length) {
                    this.setState({end: true})
                }
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
            <ul>
                {
                    this.state.end
                        ? <img src={EndPoint} alt={this.props.name}/>
                        : <img onClick={this.onClick} src={this.state.image} alt={this.props.name}/>
                }
                <span className='cursor'>{this.props.name}</span>
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
        )
    }
}

export default Node;
