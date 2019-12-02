import React, {Component} from 'react';
import {connect} from 'react-redux';
import Open from '../images/open.png';
import Close from '../images/close.png';
import EndPoint from '../images/end_point.png';
import {findTheNodeSelector, getFilter} from '../selectors/selectors';
import {closeNode, openNode, showNode} from '../actions/TreeActions';
import NodeWrapper from "./NodeWrapper";

class Node extends Component {

    onClick = () => {
        this.props.target.childTreeNodes.length
            ? this.closeNode()
            : this.openNode()
    };

    openNode = () => {
        this.props.open(this.props.id);
    };

    closeNode = () => {
        this.props.close(this.props.id);
    };

    render() {
        return (
            <ul>
                <div className={this.props.filter ? '' : 'HiddenNode'}>
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
                </div>
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
        target: findTheNodeSelector(state, ownProps.id).node,
        filter: getFilter(state, ownProps.id)
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        open: id => dispatch(openNode(id)),
        close: id => dispatch(closeNode(id)),
        show: item => dispatch(showNode(item))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Node);
