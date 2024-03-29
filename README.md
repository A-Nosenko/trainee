## Database Viewer 2019. 
Supports working with MySQL database. Intended to view database structure.

## Installation
1. You must already have JDK, Maven and Node.js installed on PC.
1. Downloading project source code from GitLab repository.
1. Starting backend server by command
```mvn spring-boot:run```
1. Downloading frontend libraries by command
```npm install``` in frontend folder
1. Starting frontend server by command
```npm start``` in frontend folder
1. You may check localhost:3000 for application start page loading.

## Usage
First the user may connect to database using IP address, port, user name and password for database.
![connection form](images_for_presentation/connection_form.png)

There is validation for input data in connection form.
User must enter:
 - Valid IP address
 - Port number from 1 to 65535
 - Login of 3 - 100 symbols
 - Password of 3 - 100 symbols
 
In case incorrect input submit form button will be blocked and user will get appropriate error messages.
    ![validation](images_for_presentation/validation.png)

Offline mode: User can press Load tree button to get saved in xml file tree.
    ![load from xml](images_for_presentation/load_from_xml.png)

Received from xml nodes have yellow color.

   ![node from xml](images_for_presentation/node_from_xml.png)

In case correct credentials input, user may connect to database and receive version of connection driver. 
    ![connection completed](images_for_presentation/connection_completed.png)

After establishing database connection user can load next nodes from database,
received from database nodes have green color.
    ![node from database](images_for_presentation/node_from_database.png)

In any time user can save tree in xml file via Save tree button.

User can open and close tree nodes, view their properties, in particular, a DDL script to create
- Views:
![view](images_for_presentation/view.png)

- Stored procedures:
![procedure](images_for_presentation/procedure.png)

- Triggers:
![trigger](images_for_presentation/trigger.png)

- Functions:
![function](images_for_presentation/function.png)

- Tables:
![tables](images_for_presentation/table.png)

- Keys:
![key](images_for_presentation/key.png)

Also, user can:
- Look column properties, ets.
![column](images_for_presentation/column.png)

- Filter nodes in tree.

    ![filter select](images_for_presentation/filter_select.png)

- Search nodes.

    ![find](images_for_presentation/find.png)
    
## Development

Application consists of two parts: 
- Backend system, developed on Java and working on port 8080.
- Frontend system, developed on JavaScript and working on port 3000.

General application schema:
![general](images_for_presentation/general.png)


### React application consists of:

- index.js:

    ![index](images_for_presentation/js_diagrams/diagram_index.png)

- App.js:

    ![app](images_for_presentation/js_diagrams/diagram_app.png)

- actions:

    ![actions](images_for_presentation/js_diagrams/diagram_actions.png)

- components:

    ![component components](images_for_presentation/js_diagrams/diagram_component_components.png)

    ![component connection_form](images_for_presentation/js_diagrams/diagram_component_connection_form.png)

    ![component node](images_for_presentation/js_diagrams/diagram_component_node.png)

    ![component node wrapper](images_for_presentation/js_diagrams/diagram_component_node_wrapper.png)

    ![component searcher](images_for_presentation/js_diagrams/diagram_component_searcher.png)

    ![component status_bar](images_for_presentation/js_diagrams/diagram_component_status_bar.png)

    ![component table](images_for_presentation/js_diagrams/diagram_component_table.png)

    ![component tree_view](images_for_presentation/js_diagrams/diagram_component_tree_view.png)

- selectors:

    ![selectors](images_for_presentation/js_diagrams/diagram_selectors.png)
    
- reducers:

    ![reducer root](images_for_presentation/js_diagrams/diagram_reducer_root.png)

    ![reducer connection](images_for_presentation/js_diagrams/diagram_reducer_connection.png)
    
    ![reducer searcher](images_for_presentation/js_diagrams/diagram_reducer_searcher.png)
    
    ![reducer tree](images_for_presentation/js_diagrams/diagram_reducer_tree.png)
    
### Spring Boot application consists of:
- class Start to run Spring Boot application:

    ![Start](images_for_presentation/java_diagrams/diagram_Start.png)
    
- config package to configure beans:

    ![config](images_for_presentation/java_diagrams/diagram_config.png)
    
- controller package to handle HTTP requests:

    ![controller](images_for_presentation/java_diagrams/diagram_controller.png)
    
- database package to work with databases:

    ![database](images_for_presentation/java_diagrams/diagram_database.png)
    
- exception package with custom Exception:

    ![exception](images_for_presentation/java_diagrams/diagram_exception.png)       
    
- file work package with classes to read and write files:
  
    ![file work](images_for_presentation/java_diagrams/diagram_file_work.png)  
    
- literals package with application constants:
    
    ![literals](images_for_presentation/java_diagrams/diagram_literals.png)  
    
- model package with application model classes:   

    ![model](images_for_presentation/java_diagrams/diagram_model.png)  
    
- parsing xml package with classes to read and write tree by xml files.
     
    ![parsing xml](images_for_presentation/java_diagrams/diagram_parsing_xml.png)
    
- service package:
    
    ![service](images_for_presentation/java_diagrams/diagram_service.png)  
    
- structure package with classes those describes application model at low level:
    
    ![structure](images_for_presentation/java_diagrams/diagram_structure.png)  
    
   
## Integration

You can connect Jenkins to this project using next pipeline script:    

```
pipeline {
	agent any
	stages {
		stage('== Update resources ==') {
			steps {
				checkout([
				    $class: 'GitSCM',
				    branches: [[name: '*/master']], 
				    doGenerateSubmoduleConfigurations: false, 
				    extensions: [], 
				    submoduleCfg: [], 
				    userRemoteConfigs: [
				        [
		    	            credentialsId: '1ffb08cf-ac0a-4b2a-97e1-084f3ccdc185',
				            url: 'https://gitlab.kharkov.dbbest.com/java-students/anatolii-nosenko-student-project.git'
				        ]
				    ]
				])
			}
		}
		
		stage ('== Stop current project ==') {
			steps {
				bat 'stop_node'
				bat 'stop_backend'
			}			
		}
		
		stage ('== Build == ') {
			parallel {
				stage ('== Starting backend server ==') {
					steps {
					    bat 'if not exist anatolii-nosenko-student-project\\temp_data mkdir anatolii-nosenko-student-project\\temp_data'
					    bat 'start_backend_tests'
						bat 'start_backend'
					}
				}
	
				stage ('== Downloading frontend libraries and start frontend server ==') {
					steps {
						bat 'npm_install'
						bat 'start_frontend_test'
						bat 'npm_start'
					}
				}
			}
		}
	}
}
```

There are bat files in project, that restarts application and you can automatically rebuild project via Jenkins 
if repository receive commit.
