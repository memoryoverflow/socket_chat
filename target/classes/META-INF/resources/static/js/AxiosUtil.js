const BASE = "/sockect";

function $get(url, params) {
    return axios({
        method: "get",
        url: BASE + url,
        params: params
    })
}

function $post(url, params) {
    return execut(BASE + url, "post", Qs.stringify(params))
}

function execut(url, method, params) {
    return axios({
        method: method,
        url: url,
        data: params
    })
}

function GetQueryValue(queryName) {
    var query = decodeURI(window.location.search.substring(1));
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == queryName) {
            return pair[1];
        }
    }
    return null;
}

//解析url参数
function parseUrl(search) {
    var obj = {};
    search = decodeURI(search);
    search = search.slice(1);
    var arr = search.split("&");
    arr.forEach(function (item) {
        var key = item.split("=")[0];
        var v = item.split("=")[1];
        obj[key] = v;
    });
    return obj;
}






