import React from 'react';
import './breadcrumb.css'
import Item from './Item'


class Breadcrumb extends React.Component {

    state = {
       links: window.location.pathname.split("/").filter(link => link)
    };


    render() {
        return(
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        {
                            this.state.links.map((link, key) => {
                            console.log("#" + link);
                            return link ? <Item
                                reference={link}
                                marker={link[0].toUpperCase() + link.slice(1)}
                                key={key}
                            /> : ""
                        })}
                    </ol>
                </nav>

            </div>
        )
    }
}

export default Breadcrumb;