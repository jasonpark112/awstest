export const requestDummyNews = async (query, start, display) => {
	const res = await fetch(`/api/dummy/newsList?query=${query}&start=${start}&display=${display}`,
		{ credentials: 'include' }
	);

	return await res.json();
};

export const requestNewsList = async (keyword, start) => {
	const res = await fetch(`/api/news?keyword=${keyword}&start=${start}`,
		{ credentials: 'include' }
	);

	return await res.json();
};

export const requestNewById = async (newsId) => {
	const res = await fetch(`/api/news/${newsId}`,
		{ credentials: 'include' }
	);

	if (res.status === 401) {
		// sessionStorage.clear();
		// window.location.href = "/login";
	}

	return await res.json();
};

export const saveNews = async (news, keyword) => {
	const response = await fetch(`/api/news/create`, {
		method: 'POST',
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			...news,
			keyword
		})
	});

	if (response.status === 401) {
		// sessionStorage.clear();
		// window.location.href = "/login";
	}
	return await response.json();
};

export const saveScrap = async (scrap, newsId) => {
	const response = await fetch(`/scrap/create`, {
		method: 'POST',
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			...scrap,
			newsId,
			memberId: 1,
		})
	})
	console.log(response);
	if (response.status === 401) {
		// sessionStorage.clear();
		// window.location.href = "/login";
	}
	// return await response.json();
}