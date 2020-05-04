// simplemde markdown editor config
var simplemde = new SimpleMDE({
    element: $("#build-editor")[0],
    autosave: {
        enabled: true,
        uniqueId: "MyUniqueID",
        delay: 1000,
    },
    spellChecker: false,
    status: false,
    tabSize: 4,
    toolbar: ["bold", "italic", "heading", "heading-bigger", "heading-smaller", "quote", "unordered-list",
        "ordered-list", "link", "image", "preview", "side-by-side", "fullscreen", "guide"],
    previewRender: function (plainText) {
        return "<div class='markdown-body'>" + markedParser(plainText) + "</div>";
    },
});

function markedParser(plainText) {
    marked.setOptions({
        renderer: new marked.Renderer(),
        gfm: true,
        tables: true,
        breaks: false,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        highlight: function (code) {
            return hljs.highlightAuto(code).value;
        }
    });
    return DOMPurify.sanitize(marked(plainText),);
}

// list item click
$("#article-list .list-item").click(function () {
    $("#article-list .list-item[class~='active']").removeClass("active");
    $(this).addClass("active");
    var aid = $(this).find("input[name='aid']").val();
    var index = $(this).find("input[name='index']").val();
    var title = $(this).find(".article-title").text();
    var content = $(this).find("p.article-excerpt").text();
    // console.log(aid);
    $("input[name='aid_input']").val(aid);
    $("input[name='aindex']").val(index);
    $("input.title-input").val(title);
    simplemde.value(content);
    $(".editor-preview.editor-preview-active").removeClass("editor-preview-active");
});
$(document).ready(function () {
    var item = $("#article-list .list-item:first-child");
    item.addClass("active");
    var aid = item.find("input[name='aid']").val();
    var title = item.find(".article-title").text();
    var content = item.find("p.article-excerpt").text();
    $("input[name='aid_input']").val(aid);
    $("input.title-input").val(title);
    simplemde.value(content);
});


// update button
$("#update-btn").click(function () {

    var data = {
        "id": $("input[name='aid_input']").val(),
        "title": $("input.title-input").val(),
        "content": simplemde.value()
    }
    data.tags = $("select[name='states[]']").val();
    console.log(data['tags']);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin/article/update",
        data: JSON.stringify(data),
        success: function (resultData) {
            var msg = resultData.msg;
            console.log(resultData);
            console.log(msg)
            $(".writting-area .notification .msg-content").text(msg);
            $(".writting-area .notification").show().delay(2000).fadeOut('fast');
            var parent = $("input[value='" + data.id + "']").parent();
            parent.find("h2.article-title").text(data.title);
            parent.find("p.article-excerpt").text(data.content);
        },
        error: function (e) {

        }
    });
});

// select2 config
$(document).ready(function () {
    $("#tags-box").select2({
        placeholder: "test7",
        maximumSelectionLength: 5,
    });
});