import React from 'react'
import { formatDistanceToNow } from 'date-fns';
import { ko } from 'date-fns/locale';

const Card = ({ title, description, link, pubDate }) => {
	return (
		<div
			className='w-[630px] h-[250px] flex flex-col justify-between p-10 border-2 border-[#e5e7eb] rounded-md shadow-sm cursor-pointer hover:border-[#bfdbfe] hover:shadow-md'
		>
			<div className='w-full truncate font-bold text-[17px]'
				dangerouslySetInnerHTML={{ __html: title }}></div>
			{/* <div>{link}</div> */}
			<div className='mt-2 text-[16px]'
				dangerouslySetInnerHTML={{ __html: description }}></div>
			<div className='mt-2 text-right self-end text-[#3b82f6] font-medium'>{formatDistanceToNow(pubDate, { addSuffix: true, locale: ko })}</div>
		</div>
	)
}

export default Card