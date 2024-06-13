import { Link } from 'react-router-dom';
const Register = () => {
    return (
        <div>
            <div classname="bg-slate-800 border border-slate-400 rounded-md p-8 shadow-lg backdrop-filter backdrop-blur-sm relative">
                <h1 className="text-4xl text-whitefont-bold text-center mb-6">Login</h1>
                <form action="">
                <div className="relative my-4">
                    <input type="email" className="block w-72 py-2.5 px-0 text-sm text-white bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:text-white focus:border-blue-600 peer" placeholder="Email"/>
                    <label htmlFor="" className="absolute text-sm text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Your Email</label>
                </div>
                <div className="relative my-4">
                    <input type="password" className="block w-72 py-2.5 px-0 text-sm text-white bg-transparent border-0 border-b-2 border-white appearance-none dark:text-white dark:border-blue dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:text-white focus:border-blue-600 peer" placeholder="Password"/>
                    <label htmlFor="" className="absolute text-sm text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Your Password</label>
                </div>
                <button type="submit" className='w-full mb-4 text-[18px] mt-6 rounded-full bg-white text-[#4389A2] hover:bg-[#4389A2] hover:text-white py-2 transition-colors duration-300" type="submit"'>Login</button>
                <div className='flex justify-between items-center'>
                    <span className="m-4"><Link className="text-blue-500" to='../Login'> Have an Account? </Link></span>
                    <span className="m-4"><Link className="text-blue-500" to='../WordSearch'> Continue </Link></span>
                </div>
                </form>
            </div>
        </div>
    );
};

export default Register;