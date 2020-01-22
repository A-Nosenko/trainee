import React from "react";
import {CSVLink} from "react-csv";
import ReactTable from 'react-table';

const columns = [
    {
        Header: 'id',
        accessor: 'id', // String-based value accessors!
    },
    {
        Header: 'data',
        accessor: 'data',

    }];

class AboutApp extends React.Component {
    constructor(props) {
        super(props);
        this.download = this.download.bind(this);
        this.state = {
            tableProperties: {
                allData: [
                    {"id": "0", "data": "123"},
                ]
            },
            dataToDownload: []
        };
    }

    loadData = () => {
        console.log("== load data ==");
        fetch('http://localhost:8080/readAll')
            .then(response => response.body)
            .then(body => {
                // let nodes = JSON.parse(data.root);
                const reader = body.getReader();
                console.log(reader.read());

            })
            .catch((e) => {
                    console.log(e.toString());
                }
            );

    };

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

    mapArray = (dataArray) => {
        let result = [];
        dataArray.forEach(item => result.push([0, item]));
        return result;
    };

    render() {
        let testData = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
        for (let i = 0; i + 2 < testData.length; i++) {
            console.log("============================");
            let pack = {};
            pack.data = this.mapArray([testData[i], testData[i + 1], testData[i + 2]]);
            pack.testing = {"test" : true, "t" : 100500};
            console.log(pack);
        }


        return (
            <div>

                <div className='button_container'>
                    <button className='btn btn-primary' onClick={this.loadData}>
                        Load data
                    </button>
                </div>

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


                <ul>
                    <li>Database Viewer 2019. Supports working with MySQL database.</li>
                    <li>Version 0.0.8</li>
                    <li>I am happy!!!</li>
                </ul>
            </div>
        )
    }
}

export default AboutApp;