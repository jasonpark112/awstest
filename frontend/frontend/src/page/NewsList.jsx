import React, { useEffect, useState } from 'react'
import DefaultLayout from '../layout/DefaultLayout'
import { requestNewsList } from '../api/newsApi';
import Card from '../component/card/Card';
import { keywordList } from '../App';
import { Link } from 'react-router-dom';

const NewsList = () => {
	const [list, setList] = useState([]);
	const [selectedKeyword, setQuery] = useState(Object.values(keywordList)[0]);
	const [start, setStart] = useState(1);

	useEffect(() => {
		requestNewsList(selectedKeyword, start).then((res) => {
			setList(res.sort((a, b) => {
				const dateA = new Date(a.pubDate);
				const dateB = new Date(b.pubDate);
				return dateB - dateA;
			}));
		});
	}, [selectedKeyword, start]);

	return (
		<DefaultLayout>
			<div className='w-full h-full p-10 flex flex-col items-center'>
				<div className='w-[600px] mt-10 flex justify-between'>
					{
						Object.values(keywordList).map((keyword, idx) => {
							return <div key={idx}
								className={(keyword === selectedKeyword
									? 'bg-[#bfdbfe]'
									: 'bg-[#e0f2fe] hover:bg-[#dbeafe] active:bg-[#bfdbfe] active:relative active:top-[1px]')
									+ ` text-[#3b82f6] px-4 py-2 rounded-md shadow-sm cursor-pointer`}
								onClick={() => { setQuery(keyword) }}
							>
								{keyword}
							</div>
						})
					}
				</div>
				<div className='w-[1400px] m-auto mt-10 grid grid-cols-2 gap-6 place-items-center'>
					{list.map((news, idx) => {
						return <Link key={idx} to={`/scrap/${news.id}`}>
							<Card title={news.title} description={news.description} link={news.link} pubDate={news.pubDate} ></Card>
						</Link>
					})}
				</div>
			</div>
		</DefaultLayout>
	)
}

export default NewsList