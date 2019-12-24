import React from "react";

class Item extends React.Component {
    render() {
        return (
            this.props.isActive ?
                <li className="breadcrumb-item active" aria-current="page">{this.props.marker}</li>
                : <li className='breadcrumb-item'><a href={this.props.reference}>{this.props.marker}</a></li>
        )
    }
}

export default Item;