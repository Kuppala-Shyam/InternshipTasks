import { Injectable, TemplateRef } from '@angular/core';
import { todo_list } from '../class/todo';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  // Array to store todo items
  todoList: todo_list[] = [
    {
      content: 'Walking',
      value: false,
    },
    {
      content: 'Break-fast',
      value: false,
    },
    {
      content: 'Reading-news paper',
      value: false,
    },
    {
      content: 'Going to college',
      value: false,
    },
  ];

  // Array to store finished todo items
  finishedList: todo_list[] = [];

  constructor(private modalService: NgbModal) {}

  // Method to add a new todo item
  addTodo(todoValue: string): boolean {
    // Check if the input value is empty
    if (todoValue.trim() === '') {
      return false; // Exit function early if input is empty
    }
    // Push a new todo item with the provided content and default value to the todoList array
    this.todoList.push({ content: todoValue, value: false });
    return true; // Return true indicating successful addition of todo item
  }

  // Method to toggle the completion status of a todo item
  changeTodo(index: number) {
    // Toggle the value of the todo item at the specified index
    this.todoList[index].value = !this.todoList[index].value;
    // Move the completed todo item to the finishedList array
    if (this.todoList[index].value) {
      const item = this.todoList.splice(index, 1);
      this.finishedList.push(item[0]);
    }
  }

  // Method to move a completed todo item back to the todoList array
  changeFinished(index: number) {
    const item = this.finishedList.splice(index, 1);
    this.todoList.unshift(item[0]);
  }

  // Method to open a modal for confirmation before deleting a todo item
  openModal(content: TemplateRef<Element>, i: number, list: todo_list[]) {
    // Open a modal with the specified content and title
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          // If the user confirms the action, remove the todo item from the appropriate list
          list.splice(i, 1);
        }
      );
  }  
}
