import React, {Component} from 'react';
import {connect} from 'react-redux';
import {getItemToShow} from '../selectors/selectors';
import ReactTable from 'react-table';
import 'react-table/react-table.css'

class Table extends Component {
    render() {
        if (!this.props.item
            || Object.entries(this.props.item.item.attributes).length === 0) {
            return (
                <div>
                </div>
            )
        }

        const content = Object.entries(this.props.item.item.attributes).sort();

        let temp = {};
        for (let [key, value] of content) {
            console.log("- " + key);
            console.log("-- " + value);
            temp[key] = value;
        }
        let data = [temp];
        console.log("## " + data);
        const columns = content.map(([key, value]) => {
            return {
                Header: <span>
                    {key.replace(/_/g, " ").toUpperCase()}
                </span>,
                id: key,
                accessor: key,
                style: {
                    textAlign: 'left',
                    whiteSpace: 'pre-wrap' // allow for words wrap inside
                },
                width: value
                    ? (value.length > 100 ? 500 : 200)
                    : 0
            }
        });

        return (
            <ReactTable
                data={data}
                columns={columns}
                className='custom-react-table'
                showPagination={false}
                defaultPageSize={1}
            />
        );
    }

}

const mapStateToProps = (state) => {
    return {
        item: getItemToShow(state).item
    }
};

export default connect(mapStateToProps)(Table)
