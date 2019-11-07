import React from "react";
import {Link} from 'react-router-dom';
import ConnectionForm from "./ConnectionForm";
import TreeView from "./TreeView";
import StatusBar from "./StatusBar";

const Header = () =>
    <header>
        <StatusBar/>
        <nav className='App-nav'>
            <Link to="connection">Connection</Link>
            <Link to="tree">Database Tree</Link>
            <Link to="about">About Program</Link>
        </nav>
    </header>;

export const Home = () =>
    <div>
        <Header/>
        <div className='center-container'>
            <h1>Welcome to Database Viewer v.1.0</h1>
        </div>
    </div>;

export const Connection = () =>
    <div>
        <Header/>
        <div className='center-container'>
            <ConnectionForm/>
        </div>
    </div>;

export const Tree = () =>
    <div>
        <Header/>
        <div className='center-container'>
            <TreeView/>
        </div>
    </div>;

export const About = () =>
    <div>
        <Header/>
        <div className='center-container'>
            <h2>Database Viewer 2019</h2>
        </div>
    </div>;

export const Error = ({path}) =>
    <div>
        <Header/>
        <div className='center-container'>
            <h2>Resource not found!</h2>
        </div>
    </div>;
