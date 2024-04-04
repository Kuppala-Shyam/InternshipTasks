# Angular Todo List App

This project is a simple Angular Todo List application built using Angular version 12.

## Overview

The Todo List app allows users to manage their daily tasks by adding new todo items, marking them as completed, and deleting them. It consists of the following components:

- **TodoComponent**: Responsible for rendering the todo list, adding new todo items, marking items as completed, and deleting them. It also handles the display of the current date and time.

- **TodoService**: Provides methods for managing todo items, such as adding new items, toggling completion status, and moving items between the todo list and finished list. It also handles the modal for confirming item deletion.

- **AppComponent**: The root component of the application. It initializes the TodoComponent.

## Usage

To run the Todo List app locally, follow these steps:

1. Clone the repository to your local machine.

2. Install dependencies using npm:

    ```
    npm install
    ```

3. Start the development server:

    ```
    ng serve
    ```

4. Open your web browser and navigate to `http://localhost:4200/` to view the app.

## Folder Structure

```
├── src/
│   ├── app/
│   │   ├── class/                  # Contains class definitions
│   │   │   └── todo.ts
│   │   ├── service/                # Contains service classes
│   │   │   └── todo.service.ts
│   │   ├── todo/                   # Contains components related to todo functionality
│   │   │   ├── todo.component.ts
│   │   │   ├── todo.component.html
│   │   │   └── todo.component.css
│   │   ├── app.component.ts        # Root component
│   │   └── app.config.ts           # Application module
├── angular.json                    # Angular configuration file
├── package.json                    # Project dependencies
└── README.md                       # This README file
```

## Additional Notes

- This project uses Bootstrap for styling and NgbModal for modal functionality.

- Unit tests for the components and services are not included in this project. You may consider adding them to ensure the reliability of the codebase.
## Acknowledgement
Special Thanks to Angular to make project easier and Thanking to Jeev Lifeworks LLP by giving this opportunity for me
