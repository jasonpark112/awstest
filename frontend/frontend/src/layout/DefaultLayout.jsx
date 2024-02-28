import React from 'react'

const DefaultLayout = ({ children }) => {
	return (
		<div className='w-screen h-screen'>
			{children}
		</div>
	)
}

export default DefaultLayout