import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = ({ children }) => {
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const navigation = useNavigate();

    const registerHandler = async (e) => {
        e.preventDefault();
        if (!name || !password) {
            return;
        }
        
        const response = await fetch("/api/register", {
            method: 'POST',
            credentials: "include",
            body: JSON.stringify({
                name,
                password
            }),
            headers: {
                "Content-Type": "application/json"
            }

        })
    
        if(response.ok) {
            const result = await response.json();
            navigation("/login");
        } else {
            console.log("회원가입 실패");
        } 
    }

    return (
        <>
          <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
            <div className="sm:mx-auto sm:w-full sm:max-w-sm">
              <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                회원가입
              </h2>
            </div>
    
            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
              <form className="space-y-6">
                <div>
                  <label htmlFor="name" className="block text-sm font-medium leading-6 text-gray-900">
                    아이디
                  </label>
                  <div className="mt-2">
                    <input
                      id="name"
                      name="name"
                      type="name"
                      autoComplete="name"
                      value={name}
                      onChange={(e)=>{setName(e.target.value)}}
                      required
                      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                    />
                  </div>
                </div>
    
                <div>
                  <div className="flex items-center justify-between">
                    <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                      비밀번호
                    </label>
                    
                  </div>
                  <div className="mt-2">
                    <input
                      id="password"
                      name="password"
                      type="password"
                      value={password}
                      onChange={(e) => {setPassword(e.target.value)}}
                      autoComplete="current-password"
                      required
                      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                    />
                  </div>
                </div>
    
                <div>
                  <button
                    
                    className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                  onClick={registerHandler}
                  >
                    회원가입
                  </button>
                </div>
              </form>
    
             
            </div>
          </div>
        </>
      )
}

export default Register