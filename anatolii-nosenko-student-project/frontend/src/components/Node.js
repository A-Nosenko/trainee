import React, {Component} from 'react';
import {connect} from 'react-redux';
import Open from '../images/open.png';
import Close from '../images/close.png';
import EndPoint from '../images/end_point.png';
import {findTheNodeSelector} from '../selectors/selectors';
import {openNode, closeNode, showNode} from '../actions/TreeActions';
import NodeWrapper from "./NodeWrapper";

class Node extends Component {

    onClick = () => {
        this.props.target.childTreeNodes.length
            ? this.closeNode()
            : this.openNode()
    };

    openNode = () => {
        this.props.open(this.props.target.item.uniqueId);
    };

    closeNode = () => {
        this.props.close(this.props.target.item.uniqueId);
    };

    render() {
        return (
            <ul>
                {
                    this.props.target.isFinalNode
                        ? <img src={EndPoint} alt={this.props.target.item.tagName}/>
                        : <img onClick={this.onClick} src={this.props.target.childTreeNodes.length ? Close : Open}
                               alt={this.props.target.item.tagName}/>
                }
                <span className={
                    this.props.target.receivedFromDatabase
                        ? 'cursor fromDatabase'
                        : this.props.target.receivedFromXML ? 'cursor fromXML' : 'cursor'
                } onClick={() => {
                    this.props.show(this.props.target.item);
                }}>{
                    this.props.target.item.tagName
                        ? this.props.target.item.tagName
                        : this.props.target.item.content
                }
                </span>
                {this.props.target.childTreeNodes.map(
                    (nodeId, key) => {
                        return (
                            <NodeWrapper id={nodeId} key={key}/>
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
        close: ip => dispatch(closeNode(ip)),
        show: item => dispatch(showNode(item))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Node);
