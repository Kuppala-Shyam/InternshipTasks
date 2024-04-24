import { UserRole } from "./userRole";


export interface User {
    userId?: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: number;
    password: string;
    balance: number;
    address: string;
    role: UserRole
}
