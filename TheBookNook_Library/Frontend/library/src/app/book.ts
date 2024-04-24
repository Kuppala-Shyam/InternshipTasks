import { User } from "./user";

export interface Book {
  bookId?: number;
  bookTitle: string;
  authorName: string;
  category: string;
  available: boolean;
  reserved: boolean;
  borrowed: boolean;
  user?:User
}
