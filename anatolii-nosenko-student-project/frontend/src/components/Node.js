import React, {Component} from 'react';
import {connect} from 'react-redux';
import Open from '../images/open.png';
import Close from '../images/close.png';
import EndPoint from '../images/end_point.png';
import {findTheNodeSelector} from '../selectors/selectors';
import {openNode, closeNode} from '../actions/TreeActions';
import NodeWrapper from "./NodeWrapper";

class Node extends Component {

    constructor(props) {
        super(props);
        this.state = {
            firstPainting: true,
            image: props.target.childTreeNodes.length ? Close : Open,
        }
    }

    onClick = () => {
        this.props.target.childTreeNodes.length
            ? this.closeNode()
            : this.openNode()
    };

    openNode = () => {
        this.setState({image: Close});
        this.setState({firstPainting: false});
        this.props.open(this.props.target.item.uniqueId);
    };

    closeNode = () => {
        this.setState({image: Open});
        this.setState({firstPainting: true});
        this.props.close(this.props.target.item.uniqueId);
    };

    render() {
        return (
            <ul>
                {
                    this.props.target.isFinalNode || (!this.state.firstPainting && !this.props.target.childTreeNodes.length)
                        ? <img src={EndPoint} alt={this.props.target.item.tagName}/>
                        : <img onClick={this.onClick} src={this.state.image} alt={this.props.target.item.tagName}/>
                }
                <span className='cursor' onClick={() => {
                    console.log(this.props.target);
                }}>{
                        this.props.target.item.tagName
                            ? this.props.target.item.tagName
                            : this.props.target.item.content
                    }
                </span>
                {this.props.target.childTreeNodes.map(
                    (node, key) => {
                        return (
                            node != null
                                ? <NodeWrapper id={node.item.uniqueId} key={key}/>
                                : <ul>null</ul>
                        )
                    }
                )}
            </ul>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        target: findTheNodeSelector(state, ownProps.id).node
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        open: ip => dispatch(openNode(ip)),
        close: ip => dispatch(closeNode(ip))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Node);
