import Home from '@/views/Home';
import Login from '@/views/Login';
import Register from '@/views/Register'
import Adoption from "@/views/Adoption";


export default [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path:'/home',
        component: Home
    },
    {
        path:'/login',
        component: Login
    },
    {
        path: '/register',
        component: Register
    },
    {
        path: '/adoption',
        component: Adoption
    }

]