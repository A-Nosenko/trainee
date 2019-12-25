import React from 'react';
import {Link} from 'react-router-dom';
import ConnectionForm from './ConnectionForm';
import TreeView from './TreeView';
import StatusBar from './StatusBar';
import Breadcrumb from "./breadcrumb/Breadcrumb";
import './components.css'

const Header = () =>
    <div>
        <header>
            <StatusBar/>
            <nav className='App-nav'>
                <Link to="connection">Connection</Link>
                <Link to="tree">Database Tree</Link>
                <Link to="about">About Program</Link>
            </nav>
        </header>
        <div className='breadcrumb_container'>
            <Breadcrumb/>
        </div>
    </div>;

export const Home = () =>
    <div>
        <Header/>
        <div className='Center-container'>
            <h1>Welcome to Database Viewer v.1.0</h1>
        </div>
    </div>;

export const Connection = () =>
    <div>
        <Header/>
        <div className='Center-container'>
            <ConnectionForm/>
        </div>
    </div>;

export const Tree = () =>
    <div>
        <Header/>
        <div className='Center-container'>
            <TreeView/>
        </div>
    </div>;

export const About = () =>
    <div>
        <Header/>
        <div>
            <div className='button_container'>
                <button className='btn btn-primary'><i className="fas fa-arrow-down"/> Download</button>
            </div>
            <div className='Center-container'>
                <h2>Database Viewer 2019. Supports working with MySQL database.</h2>
                <h2>Version 0.0.8</h2>
                <h1>I am happy!!!</h1>
            </div>
        </div>
    </div>;

export const Error = () =>
    <div>
        <Header/>
        <div className='Center-container'>
            <h2>Resource not found!</h2>
        </div>
    </div>;
