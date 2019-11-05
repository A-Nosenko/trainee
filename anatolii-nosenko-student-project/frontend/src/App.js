import React, {Component} from 'react';
import {BrowserRouter, Route, Switch} from "react-router-dom";

import {
    Home,
    Connection,
    Tree,
    About,
    Error
} from './components/components.js'

class App extends Component {
    render() {
        return (
            <div>
                <BrowserRouter>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/connection" component={Connection}/>
                        <Route exact path="/tree" component={Tree}/>
                        <Route exact path="/about" component={About}/>
                        <Route exact component={Error}/>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
