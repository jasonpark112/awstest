import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom';
import { requestNewById, saveScrap } from '../api/newsApi';
import _ from 'lodash';

const ScrapForm = () => {
	const params = useParams();
	const navigation = useNavigate();
	const { newsId } = params;
	const [news, setNews] = useState({});
	const [scrap, setScrap] = useState({
		title: '',
		content: '',
		comment: ''
	})

	const onSave = () => {
		saveScrap(scrap, newsId).then(result => {
			console.log(result)
			// navigation(-1)
		}
		).catch()
	}

	// if (!newsId) {
	// 	return;
	// }
	const validationCheck = () => {
		if (_.isEmpty(scrap.title) || _.isEmpty(scrap.comment) || _.isEmpty(scrap.content)) {
			return true
		} else {
			return false;
		}
	}

	useEffect(() => {
		requestNewById(newsId)
			.then((res) => {
				setNews(res)
			});
	}, []);

	return (
		<div className="my-20 mx-20 bg-white-100 h-screen flex flex-col justify-start text-center">
			<div className="text-black text-start space-y-6 my-10">
				<h1 className="text-4xl font-bold ">{news.title}</h1>
				<p className="mt-7 text-lg ">{news.description}</p>
				<p>
					<a target="blank" href={news.link} className="text-lg mt-7">
						<small className="block">링크로 이동하기</small>
					</a>
				</p>
			</div>

			<form className="flex flex-col">
				<input value={scrap.title} type="text" onChange={e => setScrap({ ...scrap, title: e.target.value })} placeholder="스크랩 제목" className="py-2 px-4 bg-gray-800 text-white rounded-md focus:outline-none mb-4" required />
				<textarea value={scrap.content} onChange={e => setScrap({ ...scrap, content: e.target.value })} placeholder="기사 내용 요약" rows="4" className="py-2 px-4 bg-gray-800 text-white rounded-md focus:outline-none mb-4 resize-none w-full" required></textarea>
				<textarea value={scrap.comment} onChange={e => setScrap({ ...scrap, comment: e.target.value })} placeholder="생각 정리" rows="4" className="py-2 px-4 bg-gray-800 text-white rounded-md focus:outline-none mb-4 resize-none w-full" required></textarea>
				<button disabled={validationCheck()} className="bg-blue-500 py-2 px-4 text-white rounded-md hover:bg-blue-600 focus:outline-none" onClick={onSave}>스크랩 하기</button>
			</form>
		</div >
	)
}

export default ScrapForm