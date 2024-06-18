export const setLocalStorage = (name: string, data: any) => {
    localStorage.setItem(name, JSON.stringify(data));
}

export const getLocalStorage = (name: string) => {
    return JSON.parse(localStorage.getItem(name)!);
}

export const removeLocalStorage = (name: string) => {
    localStorage.removeItem(name)
}