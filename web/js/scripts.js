function confirmPostDeletion() {

    var answer = confirm('This operation is irreversible! Are you sure you want to proceed?');

    if (answer)
        return true;
    else
        return false;
}