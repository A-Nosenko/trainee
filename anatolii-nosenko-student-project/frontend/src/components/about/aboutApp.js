import React from "react";
import {CSVLink} from "react-csv";
import ReactTable from 'react-table';

const columns = [
    {
        Header: 'name',
        accessor: 'name', // String-based value accessors!
    },
    {
        Header: 'age',
        accessor: 'age',

    }];

class AboutApp extends React.Component {
    constructor(props) {
        super(props);
        this.download = this.download.bind(this);
        this.state = {
            tableProperties: {
                allData: [
                    {"name": "ramesh", "age": "12"},
                    {"name": "bill", "age": "13"},
                    {"name": "arun", "age": "9"},
                    {"name": "kathy", "age": "21"}
                ]
            },
            dataToDownload: []
        };
    }

    download = () => {
        const currentRecords = this.reactTable.getResolvedState().sortedData;
        console.log("===============");
        console.log(currentRecords);

        // let data_to_download = [];
        // for (let index = 0; index < currentRecords.length; index++) {
        //     let record_to_download = {};
        //     for (let colIndex = 0; colIndex < columns.length; colIndex++) {
        //         record_to_download[columns[colIndex].Header] = currentRecords[index][columns[colIndex].accessor]
        //     }
        //     data_to_download.push(record_to_download)
        // }

        //////////////////////////////////////////////////////////////////////
        let data_to_download = currentRecords.map(record => record._original);
        //////////////////////////////////////////////////////////////////////

        console.log("---------------");
        console.log(data_to_download);
        console.log("===============");
        this.setState({dataToDownload: data_to_download}, () => {
            // click the CSVLink component to trigger the CSV download
            this.csvLink.link.click();
        });
    };

    render() {
        return (
            <div>
                <div className='button_container'>
                    <button className='btn btn-primary' onClick={this.download}><i className="fas fa-arrow-down"/>
                        Download
                    </button>
                </div>

                <CSVLink
                    data={this.state.dataToDownload}
                    filename="data.csv"
                    className="hidden"
                    ref={(r) => this.csvLink = r}
                    target="_blank"/>

                <ReactTable ref={(r) => this.reactTable = r}
                            data={this.state.tableProperties.allData}
                            columns={columns}
                            filterable={true}
                            //defaultFilterMethod={(filter, row) =>
                            //    String(row[filter.id]).toLowerCase().includes(filter.value.toLowerCase())}
                />


                <div className='Center-container'>
                    <h2>Database Viewer 2019. Supports working with MySQL database.</h2>
                    <h2>Version 0.0.8</h2>
                    <h1>I am happy!!!</h1>
                </div>
            </div>
        )
    }
}

export default AboutApp;