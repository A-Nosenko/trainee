import React, {Component} from 'react';
import {connect} from "react-redux";

class StatusBar extends Component {
    render() {
        return(
        <div className='Status-bar-div'>
            {(this.props.status)
                ? (<span>{this.props.status} Root: {this.props.root}</span> )
                        :(<span className='error'>Disconnected</span>)}
        </div>
        );
    }
}

const mapStateToProps = (state) => {
  return {
      status: state.connectionReducer.connectionStatus,
      root: state.connectionReducer.rootId
  }
};

export default connect(mapStateToProps)(StatusBar);
