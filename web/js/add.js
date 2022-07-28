function urlSearch(key, val) {
    var url = new URL(window.location.href);
    url.searchParams.set(key, val);
    document.location.search = url.search;
}

function search() {
    var url = new URL(window.location.href);
    var valSearch = encodeURI($("#inputSearch").val());
    var isByName = $("#byNameCheckSearch")[0].checked;
    var isByEmail = $("#byEmailCheckSearch")[0].checked;
    if (isByName)
        url.searchParams.set("name", valSearch);
    if (isByEmail)
        url.searchParams.set("email", valSearch);
    if (!isByName)
        url.searchParams.set("name", "");
    if (!isByEmail)
        url.searchParams.set("email", "");
    if (!isByName && !isByEmail)
        url.searchParams.set("name", valSearch);
    document.location.search = url.search;
}

